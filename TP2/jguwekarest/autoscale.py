# -*- coding: utf-8 -*-

import subprocess
import time

CPU_THRESHOLD = 60
CPU_THRESHOLD_DOWN = 5
INIT_TIME = 10

class Container:
    def __init__(self, name, cpu_usage):
        self.name = name
        self.cpu_usage = float(cpu_usage)
        self.init_time = time.time()

    def update(self, cpu_usage):
        self.cpu_usage = float(cpu_usage)

    def __str__(self):
        return self.name + " " + str(self.cpu_usage)

def get_stats():
    bashCommand = "bash get-docker-stats.sh"
    output = subprocess.check_output(bashCommand.split()).strip()
    stats = []
    for line in output.split("\n"):
        stats.append(tuple(line.split()))
    return stats

def scale_up(containers):
    bashCommand = "docker-compose scale jguweka=" + str(len(containers) + 1)
    print(bashCommand)
    output = subprocess.check_output(bashCommand.split()).strip()
    print(output)

def scale_down(containers):
    bashCommand = "docker-compose scale jguweka=" + str(len(containers) - 1)
    print(bashCommand)
    output = subprocess.check_output(bashCommand.split()).strip()
    print(output)

def autoscale(containers):
    now = time.time()
    stats = get_stats()

    # detect new containers
    for s in stats:
        if s[0] not in containers:
            print("New container detected", s[0])
            containers[s[0]] = Container(s[0], s[1])

    # detect old containers
    names_to_remove = []
    for c in containers.values():
        found = False
        for s in stats:
            if s[0] == c.name:
                found = True
                break
        if not found:
            names_to_remove.append(c.name)

    # actually remove old containers
    for name in names_to_remove:
        print("Removing ", name)
        del containers[name]

    # update cpu_usage
    for s in stats:
        containers[s[0]].update(s[1])
    
    # check to scale up
    for container in containers.values():
        if now - container.init_time > INIT_TIME and container.cpu_usage > CPU_THRESHOLD:
            scale_up(containers)

    # check to scale down
    average = 0
    for container in containers.values():
        average += container.cpu_usage
    average /= len(containers)

    if len(containers) > 1 and average < CPU_THRESHOLD_DOWN:
        scale_down(containers)
            
    print([c.__dict__ for c in containers.values()])

def main():
    containers = { stat[0]: Container(stat[0], stat[1]) for stat in get_stats() }
    while True:
        autoscale(containers)
        time.sleep(2)


if __name__ == "__main__":
    main()

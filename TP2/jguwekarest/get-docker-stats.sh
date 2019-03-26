docker stats --no-stream | grep jguwekarest_jguweka | cut -d' ' -f9-12 | sed 's/%$//g'

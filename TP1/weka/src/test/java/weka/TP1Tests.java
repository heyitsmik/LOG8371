/*
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/*
 * Copyright (C) 2005 University of Waikato, Hamilton, New Zealand
 */

package weka;

import junit.framework.Test;
import junit.framework.TestSuite;
import weka.clusterers.FarthestFirstTest;
import weka.test.WekaTestSuite;

/**
 * Test class for all clusterers. Run from the command line with: <p/>
 * java weka.clusterers.AllTests
 *
 * @author FracPete (frapcete at waikato dot ac dot nz)
 * @version $Revision$
 */
public class TP1Tests
  extends WekaTestSuite {

  public static Test suite() {

    TestSuite suite = new TestSuite();

    suite.addTest(FarthestFirstTest.suite());

    return suite;
  }

  public static void main(String []args) {
    junit.textui.TestRunner.run(suite());
  }
}

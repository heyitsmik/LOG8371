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
import weka.classifiers.bayes.NaiveBayesTest;
import weka.classifiers.bayes.BayesNetTest;
import weka.clusterers.FarthestFirstTest;
import weka.clusterers.SimpleKMeansTest;
import weka.associations.AprioriTest;
import weka.test.WekaTestSuite;

/**
 * Test class for...
 *    - classifiers: NaiveBayes and BayesNet
 *    - clusterers: FarthestFirst and SimpleKMeans
 *    - associators: Apriori
 */
public class TP1Tests
  extends WekaTestSuite {

  public static Test suite() {

    TestSuite suite = new TestSuite();

    suite.addTest(NaiveBayesTest.suite());
    suite.addTest(BayesNetTest.suite());
    suite.addTest(FarthestFirstTest.suite());
    suite.addTest(SimpleKMeansTest.suite());
    suite.addTest(AprioriTest.suite());

    return suite;
  }

  public static void main(String []args) {
    junit.textui.TestRunner.run(suite());
  }
}

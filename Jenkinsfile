pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "./gradlew assemble"
            }
        }
        stage('Test') {
                    steps {
                        sh "./gradlew assemble"
                    }
        }


    }
}
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */

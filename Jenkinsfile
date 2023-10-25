pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean compile'
            }
        }


                stage('SonarQube Analysis') {
               steps {
                              sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=dorra'
                          }
                    }
               stage('Test with Maven') {
                       steps {
                                      sh 'mvn test'
                                  }
                            }
stage('Deploy to Nexus') {
            steps {
                   script {
                       def mavenHome = tool 'Maven'
                       def mavenSettings = readFile "${mavenHome}/conf/settings.xml"
                       def mavenCommand = "${mavenHome}/bin/mvn"
                       sh "${mavenCommand} -s ${mavenSettings} deploy -DskipTests"
                   }
               }
        }
    }

    post {
        success {
            echo 'Build successful'
        }
        failure {
            echo 'fail'
        }
    }
}

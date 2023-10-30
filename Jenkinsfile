pipeline {
    agent any
    environment {

        DOCKER_IMAGE_NAME = 'dorra22/springkhaddem'
        DOCKER_IMAGE_TAG = 'v1'
    }
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
                sh 'mvn deploy -DskipTests'
            }
        }

          stage('building image') {
                    steps {
                        sh 'docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG -f Dockerfile .'
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

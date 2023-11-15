pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME = 'dorra22/dorrakadri-5twin4-g2-kaddem'
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
                sh 'mvn clean package -DskipTests'
            }
        }
            stage('Test with Maven') {
                    steps {
                        sh 'mvn test'
                    }
                }
        stage('SonarQube Analysis') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=dorra'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }
        stage('building image') {
            steps {
                sh 'docker build -t $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG -f Dockerfile ./'
            }
        }
        stage('push to dockerhub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_HUB_USERNAME', passwordVariable: 'DOCKER_HUB_PASSWORD')]) {
                    sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                }
                sh "docker push $DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG"
            }
        }
        stage('Run Spring && MySQL Containers') {
            steps {
                sh 'docker compose up -d'
                echo 'Run Spring && MySQL Containers'
            }
        }
    }
    post {
        success {
            mail to: "dora.kadri@esprit.tn",
            subject: "Pipeline Backend Success",
            body: " project kaddem  Backend : Success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL},Changes included in this build: ${changelog}

            "
        }
        failure {
            mail to: "dora.kadri@esprit.tn",
            subject: "Pipeline backend Failure",
            body: "project kaddem  Backend : Failure on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} "
        }
    }
}
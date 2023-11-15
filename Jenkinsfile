pipeline {
    agent any

    environment {
            SONARQUBE_URL = 'http://192.168.33.10:9000'
            SONARQUBE_USERNAME = 'admin'
            SONARQUBE_PASSWORD = 'sonarqube'
           
        }

    stages {
        stage('Clone') {
            steps {
                script {
                    git branch: 'OumaymaHAJRI-5TWIN4-G2', credentialsId: 'ssh', url: 'https://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@github.com/lotfi0310/5TWIN4_G2_KADDEM.git'

                }
            }
        }
        stage('Maven Clean') {
                    steps {
                        sh 'mvn clean'
                    }
                }

        stage('Maven Compile') {
                    steps {
                        sh 'mvn compile'
                    }
                }

        stage('SonarQube Analysis') {
                    steps {
                        script {
                            sh "mvn sonar:sonar -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_USERNAME} -Dsonar.password=${SONARQUBE_PASSWORD}"
                        }
                    }
        }
        stage('Deployment') {
                    steps {
                        script {
                            sh 'mvn deploy -DskipTests'
                        }
                    }
        }

    }
}

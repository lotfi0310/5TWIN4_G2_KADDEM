def notifySuccess() {
    def imageUrl = 'https://www.weodeo.com/wp-content/uploads/2023/02/DevOps-scaled.webp'
    def imageWidth = '800px'
    def imageHeight = 'auto'

    def consoleLog = readFile("${JENKINS_HOME}/jobs/${JOB_NAME}/builds/${BUILD_NUMBER}/log")
    def logFile = "${WORKSPACE}/console.log"
    writeFile file: logFile, text: consoleLog

    emailext(
        body: """
            <html>
                <body>
                    <p>The Jenkins job runed successfuly.</p>
                    <p>You can view the build at: <a href="${BUILD_URL}">${BUILD_URL}</a></p>
                    <p><img src="${imageUrl}" alt="Your Image" width="${imageWidth}" height="${imageHeight}"></p>
                    <p>Console Log is attached.</p>
                </body>
            </html>
        """,
        subject: "Jenkins Job - Success",
        to: 'ahmed.benguebila@esprit.tn',
        attachLog: true,  // Attach the log file
        attachmentsPattern: logFile,  // Specify the file to attach
        mimeType: 'text/html'
    )
}
pipeline {
    agent any
    stages {
        stage('GIT') {
            steps {
                checkout scm
            }
        }

        stage('COMPILING') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('SONARQUBE') {
                     steps {
                            sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ahmed2000'
                           }
                     }
       stage('JUNIT/MOCKITO') {
                                      steps {
                                              sh 'mvn test'
                                            }
                                        }
         stage('Nexus') {
                    steps {
                           sh 'mvn deploy -DskipTests=true'
                                }
                         }


         stage('Docker images')
                 {
                      steps {
                         sh 'docker build -t kaddemimage:v${BUILD_NUMBER} -f Dockerfile ./'
                               }

                 }
          stage('dockerhub') {
                                           steps {

                                      sh "docker login -u ahmed1990909 -p ahmed2000"
                                      sh "docker tag kaddemimage:v${BUILD_NUMBER} ahmed1990909/ahmedbenguebila-5twin4-g2-kaddem:kaddemimage"
                                      sh "docker push  ahmed1990909/ahmedbenguebila-5twin4-g2-kaddem:kaddemimage"
                                           }
                     }
           stage('run docker compose and kaddem project') {
                                           steps {

                                             sh 'docker compose up -d'
                                                  }
                                              }

           stage('Email Notification') {
                       steps {
                           script {
                               currentBuild.resultIsBetterOrEqualTo('SUCCESS') ? notifySuccess() : notifyFailure()
                           }
                       }
                   }



    }
    post {
        success {
            echo 'Build successfully'
        }
        failure {
            echo 'failed '
        }
    }
    }

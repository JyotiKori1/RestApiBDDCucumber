pipeline {
    agent any

    tools {
        maven "Maven"
    }

    stages {

        stage ('Testing Stage') {
            steps {

                      sh "${mvnHome}/bin/mvn clean install -Dmaven.test.failure.ignore=false -Denv.HOME=${ENVIRONMENT} -Dhub=hubtest -Dcucumber.options='-t @${SUITE}'"
            }
        }

        stage('Generate HTML report') {
           steps {
              cucumber buildStatus: 'UNSTABLE',
                reportTitle: 'My report',
                fileIncludePattern: '**/*.json',
                trendsLimit: 10,
                classifications: [
                    [
                        'key': 'Browser',
                        'value': 'Firefox'
                    ]
                ]
           }
       }


    }

     post {
            always {
                cucumber '**/cucumber.json'
            }
         }
}
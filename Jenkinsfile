pipeline {
    agent any

    parameters {
        string defaultValue: 'https://6e24-31-202-41-92.ngrok-free.app', name:'baseUrl'
        choice choices: ['api', 'ui'], name:'suite'
        booleanParam defaultValue: false, name: 'local'
    }

    tools {
        maven 'maven'
        jdk 'JDK_17'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn --version'
                sh 'java --version'
                echo "Build number $env.BUILD_NUMBER"
                sh "mvn clean test -DbaseURL=$baseURL -Dsuite=$suite -DbrowserName=chrome -Dheadless=false -Dlocal=$local"
            }

            post {
                always{
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results:[[path: 'target/allure-results']]
                        ])
                }
            }
        }
    }
}
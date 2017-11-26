# Upload TERBINE Boulder Jar to File
mvn install:install-file -Dfile=boulder-1.1.jar -DgroupId=com.terbine -DartifactId=boulder -Dversion=1.1 -Dpackaging=jar
# Upload TERBINE Cabinet Jar to File
mvn install:install-file -Dfile=cabinet-1.1.jar -DgroupId=com.terbine -DartifactId=cabinet -Dversion=1.1 -Dpackaging=jar

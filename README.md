## Differential Synchronization Android Demo
This project provides an Android demo similar to the JavaScript demo created for 
the [Differential Synchronization proof-of-concept](https://github.com/danbev/aerogear-sync-server/tree/differential-synchronization).

This is very much a work in progress and my first attempt at working with Android. This should in no way be considered
the propery way to do Android development. 

The project consists of two modules:  
1. [diffsync-demo](./diffsync-demo) which contains the actual demo  
2. [diffsync-demo-test](./diffsync-demo-test) which contains the test for the demo.

## Prerequisites
An Android device or emulator is required to run the demo and test. During testing we have successfully used 
[genymotion](http://www.genymotion.com/).  

You currently have to build aerogear-android-sync [client-refactoring](https://github.com/danbev/aerogear-android-sync/tree/client-refactoring)
branch manually. 

The 
[JSON Patch Differential Synchronization server](https://github.com/danbev/aerogear-sync-server/tree/refactoring-spi/server/server-netty#jsonpatchdiffsyncserver) 
must be running.


## Configuration
Update [strings.xml](./src/res/values/strings.xml) and change the _senderId_ to match your project number from
[google developer console](https://console.developers.google.com/project).

## Building and Testing

    mvn install
    
    
## Deploy

    cd diffsync-demo
    mvn android:deploy
    
    
## Run

    cd diffsync-demo
    mvn android:run


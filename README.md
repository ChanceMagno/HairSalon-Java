# _HairSalon_

#### _HairSalon, 03-31-2017_

#### By Chance Magno

## Description
_Application will help manage clients and stylist, assigning clients to stylist, removing/adding clients/stylist._


## Specifications

| Behavior                   | Input Example     | Output Example    |
|:---:|:---:|:---:|
| Able to add Stylist and Details|New Stylist: "Jenny"| Stylist added "Jenny" Expertise "Hair Dye"|
|Able to add Client and Details |New Client : "Jim" Style: "Hair Dye"|Client added "Jim" Style "Hair Dye" |
|Able to assign Clients to Stylist|Client "Jim" assign to "jenny"|"Jenny" Clients = "Jim"|
|Able to delete Stylist|remove "Jenny"|"jenny" Deleted|
Clients assigned to removed Stylist need to be moved to unassigned clients|Delete Stylist "Jenny"|"Jim" moved to unassigned Clients|
|Able to delete Client|remove "Jim"|"Jim" Deleted|



## Setup/Installation Requirements

* This repository is meant to be viewed. It can be viewed [here](https://ChanceMagno.github.io/Event-Coordinator-Java
).

### Or you can clone OR download a local instance of the site:

* Clone
  * Open your terminal program
    * On a Mac, this would be in the Applications/Utilities directory, and is called, "Terminal"
    * Windows uses a Terminal program as well, but a Terminal with all the capabilities we'll require is not installed by default. Thankfully, we can easily download and install a Terminal program that does fit our needs.
There are many options available, but we recommend using a terminal program called git bash. You can download this free program at [msysgit.github.io](https://ChanceMagno.github.io/HairSalon-Java
).
  * Clone this track survey repository by typing, `git clone(https://ChanceMagno.github.io/HairSalon-Java
)`
* Download
  * Click [here](https://ChanceMagno.github.io/HairSalon-Java
/archive/master.zip) to download the repo
  * Unzip the zipped repository
* For launching the program if you are unsure visit [here](https://www.learnhowtoprogram.com/java/java-applications-ff6bacd3-bc1c-4c32-87c5-cc963b704cc2/compiling-and-running-a-java-program)
*_In PSQL # CREATE DATABASE hair_salon; then run command(in terminal or command line if pc) $ psql hair_salon < hair_salon.sql;
* _Run the command 'gradle run'_
* _Open browser and go to localhost:4567_


### License

Copyright (c) 2017

This software is licensed under the MIT license.

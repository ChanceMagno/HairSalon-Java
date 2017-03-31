# _HairSalon_

#### _HairSalon, 03-31-2017_

#### By Chance Magno

## Description
_Application will help manage clients and stylist, assigning clients to stylist, removing/adding clients/stylist._


## Specifications

| Behavior                   | Input Example     | Output Example    |
| Able to add Stylist and Details|New Stylist: "Jenny"| Stylist added "Jenny" Expertise "Hair Dye"|
|Able to add Client and Details |New Client : "Jim" Style: "Hair Dye"|Client added "Jim" Style "Hair Dye" |
|Able to assign Clients to Stylist|Client "Jim" assign to "jenny"|"Jenny" Clients = "Jim"|
|Able to delete Stylist|remove "Jenny"|"jenny" Deleted|
Clients assigned to removed Stylist need to be moved to unassigned clients|Delete Stylist "Jenny"|"Jim" moved to unassigned Clients|
|Able to delete Client|remove "Jim"|"Jim" Deleted|



## Setup/Installation Requirements

* _Clone the repository_
*_In PSQL # CREATE DATABASE hair_salon; then run command $ psql media < media.sql;
* _Run the command 'gradle run'_
* _Open browser and go to localhost:4567_


### License

Copyright (c) 2017

This software is licensed under the MIT license.

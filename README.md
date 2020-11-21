Original App Design Project:
===

# BatteryHero

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
This app tries to conserve as much of your phones battery as possible when it gets to a certain level so you can still use your phone in case of an emergency.

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Utility
- **Mobile:** Not much to write, Mobile is a given.
- **Story:**  When your phone gets to say 15%, you want to save as much of it as possible to be able to use it in case of an emergency. It would do this by closing all unnecessary background apps and processes. It would also only allow critical apps to function like the Phone, Messaging, Contacts, etc. apps.
- **Market:** Anybody who uses a phone.
- **Habit:** Users would want to activate/use the app when they notice their phone battery is lowering.
- **Scope:** V1 would alert you if your battery level hits a certain percentage. V2 would close the apps that are running in the background. V3 would block apps from opening except for messages, phone, contacts, etc.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* Choosing the battery level
* Checking current battery level
* Battery level alert
* Close background apps

**Optional Nice-to-have Stories**

* Block unnecessary apps
    * If battery level is above threshold, unblock apps
    * If charging, it stops blocking apps
* A view of your apps for you to decide which apps to block
* Info page explaining what it does
* Can't choose a battery level lower than 15
* Can't change app settings after it is activated
* Better interface

### 2. Screen Archetypes

* Stream:
    * Option to choose battery threshold - default is 15%
    * List of current apps installed with the option to choose which to block when battery level is below threshold
* Detail (Optional Info Page):
    * Explanations for how to use the app

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Options Page
* Info Page

**Flow Navigation** (Screen to Screen)

* None

## Wireframes
<img src="https://github.com/TheTechThree/BatteryHero/blob/main/wireframe0.jpg" width="400">

<img src="https://github.com/TheTechThree/BatteryHero/blob/main/wireframe1.jpg" width="400">

## Schema 
### Models
Property: setLevel Type: Int Description: Battery level threshold
### Networking
-None

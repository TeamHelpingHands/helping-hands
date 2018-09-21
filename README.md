# Helping Hands
###### HH is a user-friendly web application designed to facilitate volunteer enrollment & participation for a given charitable event. Volunteers have the ability to apply to help an organization/event, follow/favorite organizations, and earn points for frequent participation! HH makes it easy to get involved, and stay involved.

## APIs
#### Google Maps
  * Purpose:
    - Ability to search for events using a radius based on zipcodes
  * Documentation:
    - https://developers.google.com/maps/documentation/
#### Google Places (Still using? @vlewisgh)
  * Purpose:
    - [COMPLETE ME]
  * Documentation:
    - https://developers.google.com/places/web-service/intro
#### Facebook (Maven dependency)
  * Purpose:
    - Ability to register & login with Facebook account
  * Documentation:
    - https://mvnrepository.com/artifact/com.facebook.api
#### Twilio
  * Purpose:
    - Ability to send out email blasts to volunteers that have signed up for an event
  * Documentation:
    - https://www.twilio.com/docs/
#### MailGun
  * Purpose:
    - Ability to send confirmation/verification emails, for registration
  * Documentation:
    - https://documentation.mailgun.com/en/latest/
#### Uber/Lyft
  * Purpose:
    - Ability to arrange a ride to event, if needed
  * Documentation:
    - Uber: https://developer.uber.com/docs
    - Lyft: https://developer.lyft.com/docs/overview
#### Filestack
  * Purpose:
    - Image hosting for profile/event pictures for users
  * Documentation:
    - https://www.filestack.com/docs/

## Features List

#### General
* Home Page
    - [x] `Registration` Button
    - [ ] `Login` Button
    - [x] `About HH` section, possibly showing participating orgs
    - [x] 3 most recent events

* _From the `Register` button..._
  - [x] Fields for Username, Password, First/Last name, zip, phone, profile picture, and...
  - [x] "Are you registering as a __VOLUNTEER__ or an __ORGANIZATION__?" radio buttons
  - [ ] Checks for existing usernames/emails to avoid duplication
  - [x] Goes to next form depending on Vol vs Org choice
  
###### The experience now branches off into two directions, depending on who the user is representing...

#### Volunteers
  * Registration
    - [x] Field for Bio
    - [ ] Sends email verification after submitting registration form
    - [x] Forwards to `Dashboard`
  * Login
    - [ ] `Username` and `Password`
    - [ ] Verifies username exists
    - [ ] Verifies password matches
    - [ ] Forwards to `Dashboard`
  * "Public" Profile
    - [x] Information about the volunteer (name, location, etc)
    - [ ] Stats about events they have attended
    - [ ] Displays `Kudos` earned/given by orgs
    - [ ] Displays comments/reviews given by organizations __(ONLY VIEWABLE BY OTHER ORGANIZATIONS)__
    - [ ] `Message` option, to send a private message
    - [ ] `Report` option, to report to admin for review
  * Dashboard
    - [ ] ~Ability to add more information pertaining to the volunteer (About me, profile picture, etc)~
    - [x] Can see their own stats about events they have attended
    - [ ] Can see most recent event attended
    - `Events` Section
      - Can show the following options about events, using tabs/cards/etc:
      - [ ] All events, ordered by date (most recent)
      - [ ] All events near me, using zipcodes
      - [ ] All events matching a category I can select
      - [ ] All events by a specific organization
    - [ ] Can see comments/reviews left by organizations
  * `Organization X` Profile, seen by a volunteer
    - [ ] Can see basic information about Org
    - [ ] Can see events created by org
    - [ ] Can select one event to go to that event's page
    - [ ] Can message Org with questions/comments
  * `Event XYZ` page, seen by a volunteer
    - [ ] TBD

#### Organizations
  * Registration
    - [x] Fields info necessary for event creation
    - [x] Field for Tax ID, for verification
    - [ ] Sends email verification after submitting registration form
    - [ ] Checks for existing emails to avoid duplication
    - [ ] Forwards to `Dashboard`
  * Login
    - [ ] `Email` and `Password`
    - [ ] Verifies email exists
    - [ ] Verifies password matches
    - [ ] Forwards to `Dashboard`
  * Dashboard
    - [ ] Ability to add more information pertaining to the Org (About us, profile picture, etc)
    - `Create an Event` button -> `Create` page
      - [ ] Event info (name, description, time, place)
      - [ ] Event banner image, at top
      - [ ] `I'm Interested` button, for volunteer to sign up
        - [ ] Clicking this button will send automated email containing information about the event, the org's application process, and any other pertinent information
        - [ ] Clicking this button will display small confirmation message, letting the volunteer know they have successfully expressed interest
    - [ ] Can see list of events created/upcoming
    - `Event` page:
      - [ ] Will show basic info about event
      - [ ] Option to "close" event because either 1) the event took place and is now over, or 2) it has been cancelled
      - [ ] Can approve/deny interested volunteers for the event
      - [ ] Able to take attendance of volunteers that actually participated
      - [ ] Able to give `kudos` and/or reviews to volunteers
    - [ ] Can see comments/reviews left by volunteers
  * "Public" Profile, viewable by volunteers
    - [ ] Basic information about Org
    - [ ] Events created by org
    - [ ] Ability to select one event to go to that event's page
    - [ ] `Message` Org with questions/comments
    - [ ] Reviews given by volunteers

* * *
## Special
#### Admin
* Registration
  - No registration form for an Admin
  - Must be manually entered into the database by existing admin
* Login
  - [ ] Will login through the volunteer `Login` page
  - [ ] Admin will have special username & password
  - [ ] Forwarded to Admin `Dashboard`
* Dashboard
  - Organization Validation
    - [ ] Will be able to click a button to Approve or Deny an organization, after verifying they are legitimate
        - This process is manual for now, however we are interested in looking into how this could be automated
  - User management
    - [ ] Able to see list of all volunteers
    - [ ] Able to disable/suspend accounts if necessary
  - Reports
    - [ ] Can see reports submitted about volunteers
    - Reports will contain information regarding:
      - Who submitted the report
      - Who the report was filed against
      - List of some reasons why
      - Additional details or information provided by reporter
    - [ ] Can either dismiss a report or take action against the volunteer
      - Admin could reach out via email, letting the volunteer know what happened and next steps

Feature: Login and Main tasks


#  @LoginCorrect
#  Scenario Outline: Verification of login correct (Zaloguj button)
#
#
#    Given Open the "<browser>" and launch "<basePage>"
#
#    When Click Login button if it shows up
#      And Enter the Username
#      And Enter the Password
#      And Click Zaloguj button
#
#    Then Check if user is logged in
#
#    Examples:
#      | browser | basePage                   |
#      | chrome  | https://primera.e-sim.org/ |
#      | firefox | https://primera.e-sim.org/ |



  @MainTaskWork
  Scenario Outline: Check Work activity


    Given Open the "<browser>" and launch "<basePage>"
      And Login correct

    When Click Work Button and go to work results

    Then  Check work results

    Examples:
      | browser | basePage                   |
      | chrome  | https://primera.e-sim.org/ |
#      | firefox | https://primera.e-sim.org/ |



  @MainTaskTrain
  Scenario Outline: Check Train activity


    Given Open the "<browser>" and launch "<basePage>"
      And Login correct

    When Click Train button and go to train results

    Then  Check train results

    Examples:
      | browser | basePage                   |
      | chrome  | https://primera.e-sim.org/ |
#      | firefox | https://primera.e-sim.org/ |
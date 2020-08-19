Feature: Login and Main tasks


  @LoginCorrect
  Scenario Outline: Verification of login correct (Zaloguj button)


    Given Open the "<browser>" and launch the "<basePage>"


    When Enter the Username and Password


    Then Click Zaloguj button

    Examples:
      | browser | basePage                   |
      | chrome  | https://primera.e-sim.org/ |
#      | firefox | https://primera.e-sim.org/ |



#  @MainTaskWork
#  Scenario Outline: Check Work activity
#
#
#    Given Open the "<browser>" and launch the "<basePage>"
#
#
#    When Login correct
#
#
#    Then Click Work button or check work result if user worked today
#
#    Examples:
#      | browser | basePage                   |
#      | chrome  | https://primera.e-sim.org/ |
##      | firefox | https://primera.e-sim.org/ |
#
#  @MainTaskTrain
#  Scenario Outline: Check Train activity
#
#
#    Given Open the "<browser>" and launch the "<basePage>"
#
#
#    When Login correct
#
#
#    Then Click Train button or check train result if user trained today
#
#    Examples:
#      | browser | basePage                   |
#      | chrome  | https://primera.e-sim.org/ |
##      | firefox | https://primera.e-sim.org/ |
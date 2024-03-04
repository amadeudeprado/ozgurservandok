import random

number = random.randint(1, 10)
tries = 1

print("Welcome to the Guessing Game!")
guess = int(input("Guess a number between 1 and 10: "))

while guess != number:
    if guess < number:
        print("Guess higher!")
    else:
        print("Guess lower!")
    guess = int(input("Try again: "))
    tries += 1

print(f"Congratulations! You guessed the number in {tries} tries!")
import random

choices = ["rock", "paper", "scissors"]

print("Rock, Paper, Scissors Game")
user_choice = input("Enter your choice (rock, paper, scissors): ").lower()
computer_choice = random.choice(choices)

print(f"Computer chose: {computer_choice}")

if user_choice == computer_choice:
    print("It's a tie!")
elif (user_choice == "rock" and computer_choice == "scissors") or \
     (user_choice == "scissors" and computer_choice == "paper") or \
     (user_choice == "paper" and computer_choice == "rock"):
    print("You win!")
else:
    print("You lose!")

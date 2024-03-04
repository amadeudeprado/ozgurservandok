name = input("What is your name? ")
print(f"Welcome to the adventure, {name}!")

choice1 = input("You reach a crossroads, do you go left or right? ").lower()

if choice1 == "left":
    choice2 = input("You've come to a river, do you swim across or go back (swim/back)? ").lower()
    if choice2 == "swim":
        print("You swam across and were eaten by an alligator.")
    else:
        print("You went back and fell into a hole. Game Over.")
elif choice1 == "right":
    choice2 = input("You've come to a bridge, do you cross it or head back (cross/back)? ").lower()
    if choice2 == "cross":
        print("You crossed the bridge and met a stranger who gave you gold. You win!")
    else:
        print("You went back and the ground collapsed behind you. Game Over.")
else:
    print("Invalid choice. Game Over.")

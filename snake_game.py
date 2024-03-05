import turtle
import time
import random

class SnakeGame:
    def __init__(self):
        self.delay = 0.1
        self.score = 0
        self.high_score = 0
        self.segments = []

        self.wn = turtle.Screen()
        self.wn.title("Snake Game")
        self.wn.bgcolor("black")
        self.wn.setup(width=600, height=600)
        self.wn.tracer(0)

        self.head = turtle.Turtle()
        self.head.speed(0)
        self.head.shape("square")
        self.head.color("white")
        self.head.penup()
        self.head.goto(0, 0)
        self.head.direction = "stop"

        self.food = turtle.Turtle()
        self.food.speed(0)
        self.food.shape("circle")
        self.food.color("red")
        self.food.penup()
        self.place_food()

        self.sc = turtle.Turtle()
        self.sc.speed(0)
        self.sc.shape("square")
        self.sc.color("white")
        self.sc.penup()
        self.sc.hideturtle()
        self.sc.goto(0, 260)
        self.update_scoreboard()

        self.wn.listen()
        self.wn.onkey(self.go_up, "Up")
        self.wn.onkey(self.go_down, "Down")
        self.wn.onkey(self.go_left, "Left")
        self.wn.onkey(self.go_right, "Right")

        self.main_game_loop()

    def go_up(self):
        if self.head.direction != "down":
            self.head.direction = "up"

    def go_down(self):
        if self.head.direction != "up":
            self.head.direction = "down"

    def go_left(self):
        if self.head.direction != "right":
            self.head.direction = "left"

    def go_right(self):
        if self.head.direction != "left":
            self.head.direction = "right"

    def move(self):
        if self.head.direction == "up":
            y = self.head.ycor()
            self.head.sety(y + 20)
        elif self.head.direction == "down":
            y = self.head.ycor()
            self.head.sety(y - 20)
        elif self.head.direction == "left":
            x = self.head.xcor()
            self.head.setx(x - 20)
        elif self.head.direction == "right":
            x = self.head.xcor()
            self.head.setx(x + 20)

    def check_collision(self):
        if (self.head.xcor() > 290 or self.head.xcor() < -290 or
                self.head.ycor() > 290 or self.head.ycor() < -290):
            self.reset_game()

        for segment in self.segments:
            if segment.distance(self.head) < 20:
                self.reset_game()

    def eat_food(self):
        if self.head.distance(self.food) < 20:
            self.place_food()
            self.extend_snake()
            self.score += 10
            self.delay *= 0.9
            self.update_scoreboard()

    def place_food(self):
        x = random.randint(-290, 290)
        y = random.randint(-290, 290)
        self.food.goto(x, y)

    def extend_snake(self):
        new_segment = turtle.Turtle()
        new_segment.speed(0)
        new_segment.shape("square")
        new_segment.color("grey")
        new_segment.penup()
        if self.segments:
            last_segment = self.segments[-1].position()
        else:
            last_segment = self.head.position()
        new_segment.goto(last_segment)
        self.segments.append(new_segment)

    def reset_game(self):
        time.sleep(1)
        for segment in self.segments:
            segment.goto(1000, 1000)
        self.segments.clear()
        self.head.goto(0, 0)
        self.head.direction = "stop"
        self.score = 0
        self.delay = 0.1
        self.update_scoreboard()

    def update_scoreboard(self):
        self.sc.clear()
        self.sc.write(f"Score: {self.score} High Score: {self.high_score}", align="center", font=("Courier", 24, "normal"))

    def main_game_loop(self):
        while True:
            self.wn.update()
            self.check_collision()
            self.eat_food()
            self.move()

            # Update the positions of the segments
            for i in range(len(self.segments)-1, 0, -1):
                x = self.segments[i-1].xcor()
                y = self.segments[i-1].ycor()
                self.segments[i].goto(x, y)
            if self.segments:
                x = self.head.xcor()
                y = self.head.ycor()
                self.segments[0].goto(x, y)

            time.sleep(self.delay)

if __name__ == "__main__":
    game = SnakeGame()
    turtle.mainloop()

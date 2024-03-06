import pygame
import random

# Initialize pygame
pygame.init()

# Set up the display
WIDTH, HEIGHT = 800, 600
window = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption("Simple Game")

# Colors
BLUE = (0, 0, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)

# Game variables
player_size = 50
enemy_size = 40
player_pos = [WIDTH // 2, HEIGHT // 2]
enemies_pos = [[50, 50], [150, 150], [250, 250]]
dancer_pos = [100, 100]
clock = pygame.time.Clock()

# Game loop
running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    # Update player position based on mouse position
    player_pos = pygame.mouse.get_pos()

    # Update enemy positions to chase the player
    for pos in enemies_pos:
        if pos[0] < player_pos[0]:
            pos[0] += 1
        elif pos[0] > player_pos[0]:
            pos[0] -= 1
        if pos[1] < player_pos[1]:
            pos[1] += 1
        elif pos[1] > player_pos[1]:
            pos[1] -= 1

    # Update dancer position randomly
    dancer_pos = [random.randrange(WIDTH - enemy_size), random.randrange(HEIGHT - enemy_size)]

    # Drawing
    window.fill((0, 0, 0))  # Fill the screen with black
    pygame.draw.rect(window, BLUE, (*player_pos, player_size, player_size))
    for pos in enemies_pos:
        pygame.draw.rect(window, RED, (*pos, enemy_size, enemy_size))
    pygame.draw.ellipse(window, GREEN, (*dancer_pos, enemy_size, enemy_size))

    # Update the display
    pygame.display.update()

    # Cap the framerate
    clock.tick(60)

pygame.quit()

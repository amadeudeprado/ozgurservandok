import numpy as np

# Define the chess board size
BOARD_SIZE = 8

# Define the pieces and their initial positions
initial_board = {
    "R": ["a1", "h1"], "N": ["b1", "g1"], "B": ["c1", "f1"],
    "Q": ["d1"], "K": ["e1"], "P": ["a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"],
    "r": ["a8", "h8"], "n": ["b8", "g8"], "b": ["c8", "f8"],
    "q": ["d8"], "k": ["e8"], "p": ["a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"]
}

# Function to create the initial chess board
def create_initial_board():
    board = np.full((BOARD_SIZE, BOARD_SIZE), '.', dtype=str)  # Empty board
    for piece, positions in initial_board.items():
        for pos in positions:
            col, row = ord(pos[0]) - ord('a'), BOARD_SIZE - int(pos[1])
            board[row, col] = piece
    return board

# Function to print the chess board
def print_board(board):
    print("  a b c d e f g h")
    print(" +----------------")
    row_number = 8
    for row in board:
        print(f"{row_number}| {' '.join(row)}")
        row_number -= 1
    print(" +----------------")

# Function to parse algebraic notation to board index
def parse_position(pos):
    """Convert board position from algebraic notation to matrix coordinates."""
    if len(pos) != 2 or not pos[0].isalpha() or not pos[1].isdigit():
        return None  # Invalid input
    col, row = ord(pos[0].lower()) - ord('a'), BOARD_SIZE - int(pos[1])
    if 0 <= col < BOARD_SIZE and 0 <= row < BOARD_SIZE:
        return (row, col)
    return None

# Function to move a piece on the board
def move_piece(board, start_pos, end_pos):
    """Move a piece on the board from start_pos to end_pos."""
    start = parse_position(start_pos)
    end = parse_position(end_pos)
    if start and end:
        # Simplified move validation: just check if the start position is not empty
        if board[start[0], start[1]] != '.':
            board[end[0], end[1]] = board[start[0], start[1]]  # Move the piece
            board[start[0], start[1]] = '.'  # Empty the original position
            return True
    return False

# Main function to play the chess game
def play_chess():
    board = create_initial_board()
    print_board(board)
    current_player = 'White'  # White starts
    while True:
        print(f"{current_player}'s turn")
        move = input("Enter your move (e.g., e2 e4): ").split()
        if len(move) == 2:
            if move_piece(board, move[0], move[1]):
                print_board(board)
                current_player = 'Black' if current_player == 'White' else 'White'
            else:
                print("Invalid move, try again.")
        else:
            print("Invalid input, please enter a move like 'e2 e4'.")

if __name__ == "__main__":
    play_chess()

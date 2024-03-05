# Importing required libraries
import tkinter as tk
from tkinter import filedialog, messagebox, simpledialog
import os

# Defining the main application class
class NotepadApp:
    def __init__(self, master):
        self.master = master
        self.master.title("Multifunctional Notepad")
        self.master.geometry("800x600")

        # Creating the text area
        self.text_area = tk.Text(self.master, undo=True)
        self.text_area.pack(fill=tk.BOTH, expand=True)

        # Creating the menu bar
        self.menu_bar = tk.Menu(self.master)
        self.master.config(menu=self.menu_bar)

        # Adding 'File' menu
        self.file_menu = tk.Menu(self.menu_bar, tearoff=0)
        self.file_menu.add_command(label="New", accelerator="Ctrl+N", command=self.new_file)
        self.file_menu.add_command(label="Open", accelerator="Ctrl+O", command=self.open_file)
        self.file_menu.add_command(label="Save", accelerator="Ctrl+S", command=self.save_file)
        self.file_menu.add_command(label="Save As...", command=self.save_as_file)
        self.file_menu.add_separator()
        self.file_menu.add_command(label="Exit", command=self.exit_app)
        self.menu_bar.add_cascade(label="File", menu=self.file_menu)

        # Adding 'Edit' menu
        self.edit_menu = tk.Menu(self.menu_bar, tearoff=0)
        self.edit_menu.add_command(label="Undo", accelerator="Ctrl+Z", command=self.undo)
        self.edit_menu.add_command(label="Redo", accelerator="Ctrl+Y", command=self.redo)
        self.edit_menu.add_separator()
        self.edit_menu.add_command(label="Cut", accelerator="Ctrl+X", command=self.cut)
        self.edit_menu.add_command(label="Copy", accelerator="Ctrl+C", command=self.copy)
        self.edit_menu.add_command(label="Paste", accelerator="Ctrl+V", command=self.paste)
        self.menu_bar.add_cascade(label="Edit", menu=self.edit_menu)

        # Binding keyboard shortcuts
        self.text_area.bind("<Control-n>", lambda event: self.new_file())
        self.text_area.bind("<Control-o>", lambda event: self.open_file())
        self.text_area.bind("<Control-s>", lambda event: self.save_file())
        self.text_area.bind("<Control-Shift-S>", lambda event: self.save_as_file())
        self.text_area.bind("<Control-z>", lambda event: self.undo())
        self.text_area.bind("<Control-y>", lambda event: self.redo())
        self.text_area.bind("<Control-x>", lambda event: self.cut())
        self.text_area.bind("<Control-c>", lambda event: self.copy())
        self.text_area.bind("<Control-v>", lambda event: self.paste())

        # Initialize filename to None (for new files)
        self.current_file = None

    # Method definitions for menu commands
    def new_file(self):
        self.text_area.delete(1.0, tk.END)
        self.current_file = None

    def open_file(self):
        file_path = filedialog.askopenfilename(defaultextension=".txt",
                                               filetypes=[("All Files", "*.*"),
                                                          ("Text Documents", "*.txt")])
        if file_path:
            self.current_file = file_path
            self.master.title(f"{os.path.basename(file_path)} - Multifunctional Notepad")
            self.text_area.delete(1.0, tk.END)
            with open(file_path, "r") as file:
                self.text_area.insert(tk.END, file.read())

    def save_file(self):
        if not self.current_file:
            self.save_as_file()
        else:
            with open(self.current_file, "w") as file:
                file.write(self.text_area.get(1.0, tk.END))
            messagebox.showinfo("Success", "File saved successfully")

    def save_as_file(self):
        file_path = filedialog.asksaveasfilename(defaultextension=".txt",
                                                 filetypes=[("All Files", "*.*"),
                                                            ("Text Documents", "*.txt")])
        if file_path:
            self.current_file = file_path
            with open(file_path, "w") as file:
                file.write(self.text_area.get(1.0, tk.END))
            self.master.title(f"{os.path.basename(file_path)} - Multifunctional Notepad")
            messagebox.showinfo("Success", "File saved as successfully")

    def exit_app(self):
        self.master.quit()

    def undo(self):
        self.text_area.event_generate("<<Undo>>")

    def redo(self):
        self.text_area.event_generate("<<Redo>>")

    def cut(self):
        self.text_area.event_generate("<<Cut>>")

    def copy(self):
        self.text_area.event_generate("<<Copy>>")

    def paste(self):
        self.text_area.event_generate("<<Paste>>")

# Function to run the application
def run_notepad_app():
    root = tk.Tk()
    NotepadApp(root)
    root.mainloop()

run_notepad_app()

#togithub
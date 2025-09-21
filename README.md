Code Explanation

Student Name: Benjamin McNeil
Student ID: 48747084

This is a repository that I forked from comp2000_2025 and renamed it to "comp2000_2025-B". This readme.md file explains how I modify the code, include the use of inheritance, interface, generics, add new functionality and improve the gameplay from branch week05.

I change the whole gameplay of the code to chess, where you can click the chess piece and select the possible move for each piece. My chess code right now might not include every chess rules and functionality, like capturing pieces, check and checkmate the king, etc., just simulating on how each piece can move.

I will go through each java file and explain what did I change from the original week 5 code, what did I add in, and how did I use inheritance, interface and generics to improve my gameplay

- Main.java
I imported "MouseEvent" and "MouseAdapter" class to use mouse click event for select, deselect and move the chess piece. I also imported "Optional" class for generics usage to store ChessSquare object, which is where mouse event really takes part. The mouse event "(MouseEvent e)" gives the coordinates of where the cursor was clicked. If any square was clicked(not outside the board), then retrieves the clicked square, which means you can interact with that square, either selecting a piece, deselecting a piece, or moving the piece to that square. Repaint the whole board after moving a piece.

- ChessBoard.java (Change name from Grid.java)
I changed from 20x20 to 8x8 grid to create a chess board, and I use "drawString" to label the square. The bottom row shows each column label (a - h) and the most-left column shows each row label (1 - 8).

- ChessSquare.java (Change name from Cell.java)
I increased each square size from 35 to 90 to perfectly fit in the window, and colored the board green and white like the default chess board color in Chess.com.

- ChessPiece.java (Change name from Actor.java)
I added an abstract method "getPossibleMoves" for displaying possible moves for each specific piece when clicked, and just added if-else statement to draw the outline of the pieces, if it's a white piece, color black outline, if it's a black piece, color white outline.

- ChessMove.java (New)
This is where I use interface for my code. I created "setLocation" method for redrawing the pieces after moving them to a new square.

- GamePlay.java (Change name from Stage.java)
This is probably the most modified java file in my code. This file is mainly for how the game works. Set the location for each piece, highlight the square if there is a piece in it when move the mouse cursor to that square, select the piece if present in that square clicked to get its possible move(highlight both the clicked square and possible moves), check if within its possible move was occupied or not (if occupied, can't move there, unhighlight since there), click on the highlighted-possible-move square to move the selected piece, and texted each move history. Generics mostly used in this file, like "List<ChessSquare> possibleMoves" contains ChessSquare object for the possible moves of each piece, "List<ChessPiece> pieces" contains ChessPiece object to draw them on the specified square, and "List<String> moveHistory" contains string objects to store each move history as a text and display them at the side of the board.

The rest of java files are all chess pieces, which are Pawn, King, Queen, Bishop, Knight and Rook. These file extends ChessPiece.java and implements ChessMove.java, so this is where most of inheritance was used in my code. Each piece file has a field to store whether its color is black or white. A constructor that taking its starting square and its color. A list that holds its shape. A getter method to check if the piece is white. Overrides the "setLocation" method implemented from ChessMove.java to clear its shape in its previous square and redraw it in a new square. Lastly, where each piece file really differentiate, is its possible move(getPossibleMoves) abstract method extended from ChessPiece.java. A pawn can only move forward one square (Except its first move, you can choose to move forward one or two squares ahead. In my code, I only write one row upward every move). The king can move in any direction one square. The queen can move 8 squares horizontally, vertically and diagonally. The bishop can only move diagonally. The rook can move horizontally and vertically, but not diagonally. The knight moves in "L" shape (2 in one direction and 1 perpendicular). 

This is every functionality in my assignment code explained. When you run the code, you might relise that there are only two pawns instead of 8 pawns. Becuase as mentioned above, this doesn't include every chess rules and gameplays of it, just recreating the chess board and each chess board and each piece movement. Therefore no capturing pieces. And because of the pawn can't move backward, if the pawns stuck in the middle of the board, then you can't really move other piece across the board. I want to show you the full range movement of other pieces too rather than just the pawns.
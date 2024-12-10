package com.example.tic_tac_toe

fun checkWinner(board: Array<Array<String>>): String? {
    // Check rows and columns
    for (i in 0..2) {
        if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0].isNotEmpty()) {
            return board[i][0]
        }
        if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i].isNotEmpty()) {
            return board[0][i]
        }
    }

    // Check diagonals
    if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0].isNotEmpty()) {
        return board[0][0]
    }
    if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2].isNotEmpty()) {
        return board[0][2]
    }

    // No winner
    return null
}
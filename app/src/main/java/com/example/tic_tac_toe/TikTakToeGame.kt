package com.example.tic_tac_toe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TicTacToeGame() {
    var board by remember { mutableStateOf(Array(3) { Array(3) { "" } }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = when {
                winner != null -> "Winner: $winner"
                board.all { row -> row.all { it.isNotEmpty() } } -> "It's a Tie!"
                else -> "Current Player: $currentPlayer"
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(8.dp)
        )

        Board(board) { row, col ->
            if (board[row][col].isEmpty() && winner == null) {
                board[row][col] = currentPlayer
                winner = checkWinner(board)
                if (winner == null) {
                    currentPlayer = if (currentPlayer == "X") "O" else "X"
                }
            }
        }

        Button(
            onClick = {
                board = Array(3) { Array(3) { "" } }
                currentPlayer = "X"
                winner = null
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(50.dp)
        ) {
            Text(text = "Restart Game", fontSize = 18.sp)
        }
    }
}

@Composable
fun Board(board: Array<Array<String>>, onCellClick: (Int, Int) -> Unit) {
    Column(
        modifier = Modifier
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        for (row in board.indices) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (col in board[row].indices) {
                    Cell(
                        value = board[row][col],
                        onClick = { onCellClick(row, col) }
                    )
                }
            }
        }
    }
}

@Composable
fun Cell(value: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .border(1.dp, Color.Black)
            .background(Color.White, shape = RoundedCornerShape(4.dp))
            .clickable { if (value.isEmpty()) onClick() }
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = if (value == "X") Color.Blue else Color.Red
        )
    }
}
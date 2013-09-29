/*
 * Copyright 2013 Studentmediene i Trondheim AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

var ball = {
	"r" : 5,
	"x" : 400,
	"y" : 600,
	"dx" : 3,
	"dy" : 3
}

var board = {
	"width" : 800,
	"height" : 640
}

var plate = {
	"width" : 250,
	"height" : 20,
	"x" : board.width / 2,
	"y" : board.height - 30
}

var mouse = {
	"x" : 400
}

var blockDimension = {
	"width" : 40,
	"height" : 20
}

var score = 0;

var blockList = new Array();

/**
 * Creates a new block at location indicated. This will automatically add it to
 * the list of blocks on the level.
 * 
 * @param column
 *            column number
 * @param row
 *            row number
 * @param color
 *            color in string hex format. ("#000000" is black "#ffffff" is
 *            white)
 */
function Block(column, row, color) {
	this.color = color;
	this.x = column * blockDimension.width;
	this.y = row * blockDimension.height;
	this.isInside = blockCheckInside;
	blockList[blockList.length] = this;
}

function blockCheckInside(x, y) {
	if (x > this.x && x < this.x + blockDimension.width) {
		if (y > this.y && y < this.y + blockDimension.height) {
			return true;
		}
	}
	return false;
}

var context;

function testRun() {
    document.getElementById("clear_me").innerHTML = "";
	score = 0;
	document.getElementById("gameScore").innerHTML = score;
	blockList = new Array();
	var c = document.getElementById("gameBoard");
	context = c.getContext("2d");
	c.addEventListener('mousemove', function(evt) {
		mouse.x = evt.clientX;
	}, false);
	for ( var i = 0; i < 5; i++) {
		new Block(1 + i, 2, "#000000");
	}

	setInterval(tick, 10);
}

function tick() {
	move();
	draw();
	movePlate();
}

function isWon() {
	for ( var i = 0; i < blockList.length; i++) {
		if (blockList[i] != null) {
			return false;
		}
	}
	return true;
}

function move() {
	var newX = ball.x + ball.dx;
	var midX = (ball.x + newX) / 2;
	var newY = ball.y - ball.dy;
	// Check for collisions with blocks
	for ( var i = 0; i < blockList.length; i++) {
		if (blockList[i] != null && blockList[i].isInside(newX, newY)) {
			// Collision occur!
			score += 100;
			document.getElementById("gameScore").innerHTML = score;
			// Check if there is collision with old x or y
			if (blockList[i].isInside(ball.x, newY)) {
				// Y caused collision
				ball.dy = -ball.dy;
			} else if (blockList[i].isInside(newX, ball.y)) {
				// X caused collision
				ball.dx = -ball.dx;
			} else {
				// both needed for collision
				ball.dy = -ball.dy;
				ball.dx = -ball.dx;
			}
			blockList[i] = null;
		}
	}
	if (ball.y < plate.y && newY >= plate.y) {
		if (ball.x > plate.x && ball.x < plate.x + plate.width) {
			ball.dy = -ball.dy;
			ball.y -= 2 * ball.dy;
			var xDiff = midX - plate.x;
			ball.dx = -5 * Math.cos(Math.PI * xDiff / plate.width);
		}
	}
	ball.x += ball.dx;
	ball.y -= ball.dy;
	if (ball.x < 0 || ball.x > board.width) {
		ball.dx = -ball.dx;
		ball.x += 2 * ball.dx;
	}
	if (ball.y < 0 || ball.y > board.height) {
		ball.dy = -ball.dy;
		ball.y -= 2 * ball.dy;
	}
}

function movePlate() {
	if (mouse.x >= plate.width / 2 && mouse.x <= board.width - plate.width / 2) {
		plate.x = mouse.x - plate.width / 2;
	}
}

function draw() {
	context.clearRect(0, 0, board.width, board.height);
	context.beginPath();
	context.fillStyle = "#ff0000";
	context.arc(ball.x, ball.y, ball.r, 0, Math.PI * 2, true);
	context.closePath();
	context.fill();
	context.fillStyle = "#00eeee";
	context.fillRect(plate.x, plate.y, plate.width, plate.height);

	for ( var i = 0; i < blockList.length; i++) {
		if (blockList[i] != null) {
			context.fillStyle = blockList[i].color;
			context.fillRect(blockList[i].x - 1, blockList[i].y - 1,
					blockDimension.width - 2, blockDimension.height - 2);
		}
	}
}
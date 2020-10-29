
$(document).ready(function () {

  $.ajax({
    type: 'GET',
    url: 'http://localhost:8080/kalahagame/start',
    dataType: "json"
  }).then(function (gameImplementation) {
    console.log(gameImplementation);
    $('#status').text(gameImplementation.status);
    var board = gameImplementation.board;
    $('#0').text(board[0]);
    $('#1').text(board[1]);
    $('#2').text(board[2]);
    $('#3').text(board[3]);
    $('#4').text(board[4]);
    $('#5').text(board[5]);

    $('#7').text(board[7]);
    $('#8').text(board[8]);
    $('#9').text(board[9]);
    $('#10').text(board[10]);
    $('#11').text(board[11]);
    $('#12').text(board[12]);

    $('kalaha-1').text(board[6]);
    $('kalaha-2').text(board[12]);

  });

  $(".pit").click(function () {

    var uiBoard = $(".pit");
    var board = [];
    var pit = this.id;

    board[6] = $('#kalaha-1')[0].innerHTML;
    board[13] = $('#kalaha-2')[0].innerHTML;

    var index;
    for (index = 0; index < uiBoard.length; ++index) {
      board[uiBoard[index].id] = uiBoard[index].innerHTML
    }

    var gamejson = {
      "board": board,
      "status": $('#status')[0].innerHTML,
      "message": "Let's Play KALAHA",
      "player1": {
        "id": 1,
        "kalahaBigPit": 6
      },
      "player2": {
        "id": 2,
        "kalahaBigPit": 13
      }
    };


    var gamePlay = {
      "pit": pit,
      "gameImplementation": gamejson

    };

    $.ajax({
      type: 'POST',
      url: 'http://localhost:8080/kalahagame/play',
      dataType: "json",
      contentType: "application/json",
      data: JSON.stringify(gamePlay)
    }).then(function (gameImplementation) {
      console.log(gameImplementation);
      $('#status').text(gameImplementation.status);
      var board = gameImplementation.board;
      $('#0').text(board[0]);
      $('#1').text(board[1]);
      $('#2').text(board[2]);
      $('#3').text(board[3]);
      $('#4').text(board[4]);
      $('#5').text(board[5]);

      $('#7').text(board[7]);
      $('#8').text(board[8]);
      $('#9').text(board[9]);
      $('#10').text(board[10]);
      $('#11').text(board[11]);
      $('#12').text(board[12]);

      $('#kalaha-1').text(board[6]);
      $('#kalaha-2').text(board[13]);
    });
  });

});

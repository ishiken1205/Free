let timer;
let runTimer;

function startTimerFirst(hour, minute, second) {
	
	// 入力値を秒単位に変換
	timer = Number(hour) * 3600 + Number(minute) * 60 +  Number(second);
	
	// 1秒ごとにタイマーを更新
	runTimer = setInterval(updateTimer, 1000);
	
	// ボタンを切り替え
	document.getElementById('start_button').innerHTML = 'ストップ';
	document.getElementById('start_button').setAttribute('onclick', 'stopTimer()');
	
}

function startTimer() {
	
	// 1秒ごとにタイマーを更新
	runTimer =  setInterval(updateTimer, 1000);
	
	// ボタンを切り替え
	document.getElementById('start_button').innerHTML = 'ストップ';
	document.getElementById('start_button').setAttribute('onclick', 'stopTimer()');
	
}

function stopTimer(){
	
	// タイマーをストップ
	clearInterval(runTimer);
	
	// ボタンを切り替え
	document.getElementById('start_button').innerHTML = 'スタート';
	document.getElementById('start_button').setAttribute('onclick', 'startTimer()');
	
}

function updateTimer() {
	
	// タイマーを1秒減らす
	timer --;
	
	if(timer == 0) {
		// タイマーが0になった場合の処理

		// タイマーをストップ
		clearInterval(runTimer);
		
		// ボタンを切り替え
		document.getElementById('start_button').innerHTML = '終了';
		document.getElementById('start_button').removeAttribute('onclick');
		document.getElementById('start_button').setAttribute('type', 'submit');
		document.getElementById('start_button').setAttribute('name', 'button_action');
		document.getElementById('start_button').setAttribute('value', 'reset');
		
	}
		
	// 時間と分と秒を文字列型で取得
	hours = String(Math.floor(timer / 3600)).padStart(2, '0');
	minutes = String(Math.floor((timer - hours * 3600) / 60)).padStart(2, '0');
	seconds = String(timer % 60).padStart(2, '0');
	
	// タイマーをビューに反映
	document.getElementById('timer').innerHTML = hours + ":" + minutes + ":" + seconds;
}
let url = "http://localhost:28852";
let stompClient = null;

$(document).ready(() => {
    connect();
    $('#send-msg-btn').on('click', sendMessage);
});

function connect() {
    let socket = new SockJS(url + '/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, () => {
        console.log("Connected to WebSocket");
        stompClient.subscribe('/topic/public', message => {
            showMessage(JSON.parse(message.body).content);
        });
    });
}

function sendMessage() {
    let inputField = $('#input-msg');
    let messageContent = inputField.val();
    if (!messageContent) return;
    stompClient.send("/app/sendMessage", {}, JSON.stringify({'content': messageContent}));
    inputField.val('');
}

function showMessage(message) {
    let newMessage = $('<div>').addClass('message').text(message);
    $('#messages').append(newMessage);
    newMessage[0].scrollIntoView({behavior: 'smooth'});
}

let url = "http://localhost:28852";
let stompClient = null;

$('#send-username-btn').on('click', () => {
    let username = $('#input-username').val().trim();
    if (!isValidUsername(username)) {
        alert("Username must be 3-15 characters long and contain only letters, numbers, or underscores.");
        return;
    }
    fetch('/join', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({username: username})
    }).then(response => {
        if (response.ok) {
            $('#username-ui').hide();
            $('#chat-ui').show();
            initializeChat(username);
        } else if (response.status === 409) {
            alert(`Username '${username}' already taken. Please choose another one.`);
        } else {
            alert("An error occurred. Please try again.");
        }
    });
});

function initializeChat(username) {
    $('#send-msg-btn').on('click', () => sendMessage(username));
    stompClient = Stomp.over(new SockJS(url + '/ws'));
    stompClient.connect({}, () => {
        fetch('/messages')
            .then(response => response.json())
            .then(history => history.forEach(showMessage));
        stompClient.subscribe('/topic/public', message => showMessage(JSON.parse(message.body)));
    });
}

function showMessage(msg) {
    let container = $('<div>').addClass('message-container');
    $('<span>').addClass('sender').text(msg.sender).appendTo(container);
    $('<span>').addClass('date').text(new Date(msg.timestamp).toLocaleTimeString()).appendTo(container);
    $('<span>').addClass('message').text(msg.content).appendTo(container);
    $('#messages').append(container);
    container[0].scrollIntoView({behavior: 'smooth'});
}

function sendMessage(username) {
    let input = $('#input-msg');
    let content = input.val().trim();
    if (!content) return;
    stompClient.send("/app/sendMessage", {}, JSON.stringify({
        sender: username,
        content: content,
        timestamp: new Date().toISOString()
    }));
    input.val('');
}

function isValidUsername(username) {
    return /^[a-zA-Z0-9_]{3,15}$/.test(username);
}

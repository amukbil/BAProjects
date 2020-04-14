let simulator, entity;
let carspeed;
let carposition = new Array(2);

function init() {
    // simulator socket
    const simSocket = new SockJS('/simulator');
    simulator = Stomp.over(simSocket);
    simulator.connect({}, function (frame) {
        console.log('connected: ' + frame);

        simulator.subscribe('/control/start',
            function (event) {
                console.log(event.body)
            });

        simulator.subscribe('/control/stop',
            function (event) {
                console.log(event.body);
            });

        simulator.subscribe('/control/resume',
            function (event) {
                console.log(event.body);
            });

    });

    // entities socket
    const carSocket = new SockJS('/entity');
    entity = Stomp.over(carSocket);
    entity.connect({}, function (frame) {
        console.log('connected: ' + frame);

        entity.subscribe('/car/data',
            function (data) {
                let l_data = JSON.parse(data.body);
                updateData(l_data.speed, l_data.position);
                console.log('car speed' + carspeed + ', position' + carposition);
            });

    });
}

function startSimulation() {
    simulator.send("/control/start", {});
}

function stopSimulation() {
    simulator.send("/control/stop", {});
}

function resumeSimulation() {
    simulator.send("/control/resume", {});
}

function updateData(speed, position ){
    carspeed = speed;
    carposition = position;
}

$(function () {
    init();
});
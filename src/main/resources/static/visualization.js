var config = {
    type: Phaser.AUTO,
    width: 800,
    height: 400,
    physics: {
        default: 'arcade',
        arcade: {
            gravity: { y: 0 },
            debug: false
        }
    },
    scene: {
        preload: preload,
        create: create,
        update: update
    }
};

var car;

var vis = new Phaser.Game(config);

function preload ()
{
    this.load.image('road', 'assets/street.png');
    this.load.image('car', 'assets/car.png');
}

function create ()
{
    this.add.image(400,200, 'road');
    car = this.physics.add.sprite(185,100, 'car');
    carposition = [185,100];

}

function update()
{
    car.x = carposition[1];
    car.y = carposition[0];
}
print('######################################### INITIALIZING MONGO DB DATA #########################################');

db = db.getSiblingDB('crawler');

db.createCollection('user');
db.user.insertMany([
    {
        _id: ObjectId('5f73c61be3139d3af97cf55d'),
        name: 'Paulo Silva',
        email:'paulosilvajp0@gmail.com'
    }
]);

db.createCollection('jobFilter');
db.jobFilter.insertMany([
    {
        userId: '5f73c61be3139d3af97cf55d',
        jobOpeningSources: [1],
        jobKeyWords: ["Java","Remoto","Remota","CLT","Spring"],
        isActive: true
    }
]);

db.createCollection('trackObject');
db.trackObject.insertMany([
    {
        userId: '5f73c61be3139d3af97cf55d',
        trackCode: 'QD416325310BR',
        description: 'teste',
        carrier: 2,
        lastEventsAmount: 4,
        isActive: true
    }
]);
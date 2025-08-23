// init-mongo.js

db = db.getSiblingDB("mydb"); // the database your app uses
db.createUser({
  user: "appuser",
  pwd: "apppassword",
  roles: [{ role: "readWrite", db: "mydb" }]
});

const express = require('express');
const router = express.Router();
const passport = require('passport');

router.post('/session/login', function(req, res, next) {
    passport.authenticate('local', function(err, user, info) {
      if (err) { return res.status(500).send(); }
      if (!user) { return res.status(404).send(); }
      req.logIn(user, function(err) {
        if (err) { return res.status(500).send(); }
        return res.status(200).send(user);
      });
    })(req, res, next);
  });

router.get('/session/logout', (req, res) => {
  req.logout();
  res.status(200).send();
});

module.exports = router;
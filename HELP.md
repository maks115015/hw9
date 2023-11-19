Java/Spring-Boot application which initializes database with users and provides REST API operations on users.

to initialize users table run with profile 'init'
target users count can be set by property 'db.init.size'
batch size for insert can be set by property 'db.init.batch.size'

to test speed for innodb_flush_log_at_trx_commit modes siege can be used:
siege -c15 -t20s 'http://localhost:8080/users/save POST'
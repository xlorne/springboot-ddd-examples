# user create
curl -X POST  http://localhost:8090/user/create -H 'Content-Type:application/json'  -d '{"username": "xx","password": "x"}'
curl -X POST  http://localhost:8090/user/create -H 'Content-Type:application/json'  -d '{"username": "xx","password": ""}'

# user list
curl -X GET  'http://localhost:8090/user/list?current=1&pageSize=20' -H 'Content-Type:application/json'

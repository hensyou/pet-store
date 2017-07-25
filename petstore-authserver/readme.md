curl -X POST --user 'petstore-ui:petstore-ui' -d 'grant_type=password&username=peter1&password=password' http://localhost:9999/oauth/token


{"access_token":"debcb606-b92c-42e2-bfc3-cbb26a1ed2b2","token_type":"bearer","refresh_token":"0ed4a552-ee19-41b0-82cd-128249a4734d","expires_in":3599,"scope":"read write"}
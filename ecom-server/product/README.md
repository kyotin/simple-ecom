# Product Service

### Deploy to minikube
Go to `./build/product` folder, then run below cmd
```
helm install  product .
```

List all services
```
kubectl get services
```

Service it
```
minikube service product
```
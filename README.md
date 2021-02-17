## domain-analytics
Minecraft plugin designed to let server administrators track player traffic from hostnames.

### Building
DomainAnalytics uses [Gradle](https://gradle.org).
```
git clone https://github.com/rxgby/domain-analytics.git
cd domain-analytics
gradle shadow
```

### Usage
* `/da <hostname>`
    * requires the `analytics.use` permission
    * outputs the amount of unique users who have joined, and the total # of connections made through a particular hostname.

### License
[Apache](https://github.com/rxgby/domain-analytics/blob/master/LICENSE) © [rxgby](https://github.com/rxgby)

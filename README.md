# Speed Router

> WIP

A custom routing plugin & client-side mod project for Velocity servers.

## Getting Started

The setup is pretty standard.
First, download the plugin from [Jenkins](https://build.zhufucdev.com/job/Speed%20Router/)
and place it in `[velocity root]/plugins`.

Next is to create some `[velocity root]/plugins/speed-router/config.yaml`, and follow
the [configuration guide](#configuration)

## Configuration

Following is an example for the config.yaml file

```yaml
default_server: "vanilla"
1.18..: "vanilla"
1.12.2: "modded_1"
1.16.5+1.17.1: "modded_2"
Legacy..=1.11.1: "legacy"
```

If you have no idea about the version codes, here's a table.

When writing the configuration, only version codes included in the
`Matching MC version` column are valid.

| Protocol version | Matching MC version |
|------------------|---------------------|
| -1               | Unknown             |
| -2               | Legacy              |
| 4                | 1.7.2 - 1.7.5       |
| 5                | 1.7.6 - 1.7.10      |
| 47               | 1.8 - 1.8.9         |
| 107              | 1.9                 |
| 108              | 1.9.1               |
| 109              | 1.9.2               |
| 110              | 1.9.3, 1.9.4        |
| 210              | 1.10 - 1.10.2       |
| 315              | 1.11                |
| 316              | 1.11.1, 1.11.2      |
| 335              | 1.12                |
| 338              | 1.12.1              |
| 340              | 1.12.2              |
| 393              | 1.13                |
| 401              | 1.13.1              |
| 404              | 1.13.2              |
| 477              | 1.14                |
| 480              | 1.14.1              |
| 485              | 1.14.2              |
| 490              | 1.14.3              |
| 498              | 1.14.4              |
| 573              | 1.15                |
| 575              | 1.15.1              |
| 578              | 1.15.2              |
| 735              | 1.16                |
| 736              | 1.16.1              |
| 751              | 1.16.2              |
| 753              | 1.16.3              |
| 754              | 1.16.4, 1.16.5      |
| 755              | 1.17                |
| 756              | 1.17.1              |
| 757              | 1.18                |

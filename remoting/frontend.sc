val conf = """
akka {
  loglevel = DEBUG
  stdout-loglevel = DEBUG
  loggers = ["akka.event.slf4j.Slf4jLogger"]
  actor {
    provider = "akka.remote.RemoteActorRefProvider"
  }
  remote {
    enabled-transports = ["akka.remote.netty.tcp"]
    netty.tcp {
      hostname = "0.0.0.0"
      port = 2552
    }
  }
  http {
    server {
      server-header = "GoTicks.com REST API"
    }
  }
}"""

val config = ConfigFactory.parseString(conf)
val frontend = ActorSystem("frontend",config)

val path = "akka.tcp://backend@0.0.0.0:2551/user/simple"
val simple = frontend.actorSelection(path)
simple ! "Hello Remote World!"
#!/bin/sh

echo "The application will start in ${SOCIAL_SLEEP}s..." && sleep ${SOCIAL_SLEEP}
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.gleisser.social.user.SocialUserServiceApplication"  "$@"

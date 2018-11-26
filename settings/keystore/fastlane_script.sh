#!/usr/bin/env bash
BUILD_VARIANT_TYPE="$1"
VERSION_TYPE="$2"
APP_TYPE="$3"
FABRIC_API_TOKEN="$4"
FABRIC_BUILD_SECRET="$5"

echo -----------------------------------------------------------------------------
echo                          Applying project setting
echo -----------------------------------------------------------------------------
echo Build Variant Type :- "$BUILD_VARIANT_TYPE"
echo Version Type :- "$VERSION_TYPE"
echo Application Type :- "$APP_TYPE"
echo Fabric Api Token :- "$FABRIC_API_TOKEN"
echo Fabric Build Secret :- "$FABRIC_BUILD_SECRET"
echo -----------------------------------------------------------------------------

function getProperty {
    grep $1 $2|cut -d'=' -f2
}

setProperty(){
  awk -v pat="^$1=" -v value="$1=$2" '{ if ($0 ~ pat) print value; else print $0; }' $3 > $3.tmp
  mv $3.tmp $3
}

setVersionNameAndCode(){
    echo "File path -> $1"
    if [ -f "$1" ]; then
        echo "$3 properties exist!"

        VERSION_MAJOR=$(getProperty "VERSION_MAJOR" "$1")
        VERSION_MINOR=$(getProperty "VERSION_MINOR" "$1")
        VERSION_PATCH=$(getProperty "VERSION_PATCH" "$1")
        VERSION_CODE=$(getProperty "VERSION_CODE" "$1")

        case $2 in
                major)
                        VERSION_MAJOR=$((VERSION_MAJOR+1))
                        VERSION_MINOR=0
                        VERSION_PATCH=0
                        VERSION_CODE=$((VERSION_CODE+1))
                        ;;
                minor)
                        VERSION_MINOR=$((VERSION_MINOR+1))
                        VERSION_PATCH=0
                        VERSION_CODE=$((VERSION_CODE+1))
                        ;;
                patch)
                        VERSION_PATCH=$((VERSION_PATCH+1))
                        VERSION_CODE=$((VERSION_CODE+1))
                        ;;
                reset)
                        VERSION_MAJOR=1
                        VERSION_MINOR=0
                        VERSION_PATCH=0
                        VERSION_CODE=1
                        ;;
                *)
                        VERSION_CODE=$((VERSION_CODE+1))
                        ;;
        esac

        setProperty "VERSION_MAJOR" "${VERSION_MAJOR}" "$1"
        setProperty "VERSION_MINOR" "${VERSION_MINOR}" "$1"
        setProperty "VERSION_PATCH" "${VERSION_PATCH}" "$1"
        setProperty "VERSION_CODE" "${VERSION_CODE}" "$1"

        echo "Version code - ${VERSION_CODE}"
        printf "Version Name - %d.%d.%d(%d)\n" "${VERSION_MAJOR}" "${VERSION_MINOR}" "${VERSION_PATCH}" "${VERSION_CODE}"
    else
        echo "$3 properties not found!!"
        exit 1
    fi
}

FABRIC_PROPERTY_FILE=../${APP_TYPE}/fabric.properties
if [ -f "${FABRIC_PROPERTY_FILE}" ]; then
    echo "fabric properties exist!"
    if [ ! -z "$FABRIC_BUILD_SECRET" -a "$FABRIC_BUILD_SECRET" != " " ]; then
        setProperty "apiSecret" "${FABRIC_BUILD_SECRET}" "${FABRIC_PROPERTY_FILE}"
    fi

    echo "Fabric Build Secret - $(getProperty "apiSecret" "${FABRIC_PROPERTY_FILE}")"
else
    echo "fabric properties not found!!"
    exit 1
fi

PROJECT_PROPERTY_FILE=../settings/versions/${APP_TYPE}/project.properties
if [ -f "${PROJECT_PROPERTY_FILE}" ]; then
    echo "projectDetail properties exist!"

    if [ ! -z "$FABRIC_API_TOKEN" -a "$FABRIC_API_TOKEN" != " " ]; then
        setProperty "FABRIC_API_TOKEN" "${FABRIC_API_TOKEN}" "${PROJECT_PROPERTY_FILE}"
    fi

    echo "Application Id - $(getProperty "APPLICATION_ID" "${PROJECT_PROPERTY_FILE}")"
    echo "Fabric Api Token - $(getProperty "FABRIC_API_TOKEN" "${PROJECT_PROPERTY_FILE}")"
else
    echo "projectDetail properties not found!!"
    exit 1
fi

case ${BUILD_VARIANT_TYPE} in
    development)
        DEV_PROPERTY_FILE=../settings/versions/${APP_TYPE}/development.properties
        setVersionNameAndCode "$DEV_PROPERTY_FILE" "$VERSION_TYPE" "development"
        ;;
    qa)
        QA_PROPERTY_FILE=../settings/versions/${APP_TYPE}/qa.properties
        setVersionNameAndCode "$QA_PROPERTY_FILE" "$VERSION_TYPE" "qa"
        ;;
    production)
        PROD_PROPERTY_FILE=../settings/versions/${APP_TYPE}/production.properties
        setVersionNameAndCode "$PROD_PROPERTY_FILE" "$VERSION_TYPE" "production"
        ;;
    *)
        echo "Sorry, I don't understand"
        ;;
esac

#Create keystore file if not exit.
KEYSTORE_FILE=../settings/keystore/release.jks
if [ -f "${KEYSTORE_FILE}" ]; then
    echo "keystore exist!"
else
    echo "keystore not found!!"
    keytool -genkey -alias androidreleasekey -keyalg RSA -keystore ${KEYSTORE_FILE} -dname "cn=localhost, ou=IT, o=Continuent, c=US" -storepass password -keypass password
fi
echo -----------------------------------------------------------------------------

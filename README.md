# soko api for java
Library to make storage in soko app. 

>website of [[SOKO File](https://soko.isjetokoss.xyz)]

> docs : [[Swagger-api](https://soko.isjetokoss.xyz/api/docs)]

How to use use it:
- Connect to website, create an account and add some API-KEY (copied this api-key value)
- Open your java projet and instanciaté a new object of Soko class:
```java
Soko soko = new Soko.Builder()
                .setApikey(YOUR_API_KEY)
                .setAppName(YOUR_APP_NAME) // noted that YOUR_APP_NAME is not very important.
                .setDebuggable(true) // If you want to get logs activities
                .build();
                
```
1 - Create new folder

```java
ResponseDTO<Folder> res = soko.folder()
                .create()
                .addName("My folder name")
                .addParent("my drive") // optional
                .execute();
// Output

```
```json
{
    "success": true,
    "presents": {
        "data": {
            "_id": "e1c45eef-536e-493a-86a5-bad77eb6a9cf",
            "name": "test - 1599000491181",
            "isBase": null,
            "createdAt": "2020-09-01 22:48:12",
            "parent": {
                "_id": "8cf06ac4-faa6-41b5-8c9b-92737e4ca6b2",
                "name": "my drive"
            }
        }
    },
    "message": "Folder saved successfully"
}
```


2 - Example for uploading file
```java
// fila is java.io.File object
String fileName = "file_test_.txt";
ClassLoader classLoader = ClassLoader.getSystemClassLoader();
java.io.File fila = new java.io.File(classLoader.getResource(fileName).getFile());

ResponseDTO<File> res = soko.file()
        .uploadFile()
        .addFile(fila)
        .folder(FOLDER_NAME_OR_ID) // the folder name or folder ID
        .execute();

// The Output
```

```json
{
    "data": {
        "_id": "0c781d81-b9ce-4594-976f-790149edd17e",
        "extension": "png",
        "size": "208.860 KB",
        "filename": "img.png",
        "url": "https://soko.isjetokoss.xyz/soko/1/my drive/01b9da3224fa4cc0ada1866d6cd1675c.png",
        "folder": {
            "_id": null,
            "name": "my drive",
            "parent": null,
            "is_base": false,
            "created_at": null
        },
        "createdBy": {
            "id": null,
            "name": "nivekaa",
            "max_api": 0,
            "space_left": 0,
            "number_files": 0,
            "updated_at": null
        },
        "createdAt": "2020-09-01 22:59:00"
    },
    "success": true,
    "status": 200,
    "message": null
}

```
It is also possible to uploade multiple File :
```java
......
  .uploadFile()
  .addFile(fila1)
  .addFile(fila2)
     ...
  .addFile(n)
  .folder(FOLDER_NAME_OR_ID)
......
```

Now if you have file encoded in base64, then you can upload this like this:
```java
ResponseDTO<File> res = soko.file()
                .createByBase64()
                .fileEncoded(FILE_BASE_64)
                .folder(FOLDER_NAME_OF_ID)
                .execute();
```


If you want to show uploading progression, you can add listener like belong:
```
soko.onEventProgress(new ProgressListener.Callback() {
            @Override
            public void progress(float percent) {
                // do something here
            }
        });
```
For lambda simply make: 
```java
soko.onEventProgress(percent -> {
  // do something here
});
```

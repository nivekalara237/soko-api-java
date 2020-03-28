package com.nivekaa.soko.util;

/**
 * @author nivekaa
 * Created 28/03/2020 at 02:05
 * Class com.nivekaa.soko.util.Data
 */

public class Data {
    public static final String fileList = "{ \"success\":true, \"presents\": { \"data\": [ { \"_id\": \"2aec4137-b791-460c-bac9-9301c25593f3\", \"extension\": \"xlsx\", \"size\": \"19.690 KB\", \"original_name\": \"text001.xlsx\", \"url\": \"http://127.0.0.1:8000/soko/2/mydrive/f72959c175054089b577bf25ed0b6456.xlsx\", \"created_at\": \"2020-03-26 19:42:44\", \"folder\": { \"name\": \"mydrive\" }, \"created_by\": { \"name\": \"soko admin updated\" } }, { \"_id\": \"4dc7adb0-ed1e-4c9c-ad5c-d75a25b89ba2\", \"extension\": \"xlsx\", \"size\": \"19.690 KB\", \"original_name\": \"text001.xlsx\", \"url\": \"http://127.0.0.1:8000/soko/2/mydrive/8c42b4cccfbe4043ab458341a8f3340b.xlsx\", \"created_at\": \"2020-03-26 19:34:22\", \"folder\": { \"name\": \"mydrive\" }, \"created_by\": { \"name\": \"soko admin updated\" } }, { \"_id\": \"587f124b-e1e0-41cf-a0b7-d3ae2c090f3d\", \"extension\": \"odt\", \"size\": \"23.959 KB\", \"original_name\": \"Sans nom 1.odt\", \"url\": \"http://127.0.0.1:8000/soko/2/mydrive/52a1e6f9356a4634b96ad9b3da5c8a5d.odt\", \"created_at\": \"2020-03-26 19:34:22\", \"folder\": { \"name\": \"mydrive\" }, \"created_by\": { \"name\": \"soko admin updated\" } }, { \"_id\": \"5bb17dbb-6216-4c6d-9a91-9dd8b08829f4\", \"extension\": \"vnd.openxmlformats-officedocument.spreadsheetml.sheet\", \"size\": \"19.747 KB\", \"original_name\": \"fa2208248b88483898093827a3f10474.xlsx\", \"url\": \"http://127.0.0.1:8000/soko/2/mydrive/fa2208248b88483898093827a3f10474.xlsx\", \"created_at\": \"2020-03-27 15:22:54\", \"folder\": { \"name\": \"mydrive\" }, \"created_by\": { \"name\": \"soko admin updated\" } }, { \"_id\": \"6734dccc-7a35-4ce6-b214-36f8b9c7f82a\", \"extension\": \"xlsx\", \"size\": \"19.747 KB\", \"original_name\": \"3a605502414c4bd0ab95e0e7a1df0f02.xlsx\", \"url\": \"http://127.0.0.1:8000/soko/2/mydrive/3a605502414c4bd0ab95e0e7a1df0f02.xlsx\", \"created_at\": \"2020-03-27 15:28:40\", \"folder\": { \"name\": \"mydrive\" }, \"created_by\": { \"name\": \"soko admin updated\" } }, { \"_id\": \"b63ecbaf-1d0f-46f3-acbd-8f34ed33cebf\", \"extension\": \"odt\", \"size\": \"23.959 KB\", \"original_name\": \"Sans nom 1.odt\", \"url\": \"http://127.0.0.1:8000/soko/2/mydrive/e1d85d825284456f91966170f04da1b6.odt\", \"created_at\": \"2020-03-26 19:42:44\", \"folder\": { \"name\": \"mydrive\" }, \"created_by\": { \"name\": \"soko admin updated\" } } ], \"meta\": { \"pagination\": { \"total\": 6, \"count\": 6, \"per_page\": 25, \"current_page\": 1, \"total_pages\": 1, \"links\": {} } } }, \"message\": null }"
            .replaceAll(" \"", "\"").replaceAll(" \\{", "{");

    public static final String singleFile = "{ \"success\":true, \"presents\": { \"data\": { \"_id\": \"994ec4c7-e1d3-480b-a45a-7dae42414a36\", \"extension\": \"png\", \"size\": \"435.953 KB\", \"original_name\": \"Capture d’écran du 2020-02-07 12-49-51.png\", \"url\": \"http://127.0.0.1:8000/soko//2987cc10450745e99aabc60346bf5b1e.png\", \"created_at\": \"2020-03-27 21:52:39\", \"folder\": { \"name\": \"test\" }, \"created_by\": { \"name\": \"soko admin updated\" } } }, \"message\": null }"
            .replaceAll(" \"", "\"").replaceAll(" \\{", "{");

    public static final String listFolder = "{ \"success\":true, \"presents\": { \"data\": [ { \"_id\": \"14f8c621-02b6-4238-92d3-3655cb294435\", \"name\": \"mydrive\", \"base\": false, \"created_at\": \"2020-03-26 19:14:26\", \"created_by\": { \"name\": \"soko admin updated\" }, \"parent\": null }, { \"_id\": \"48043fcc-ac1c-43bb-8d0d-0031e9166595\", \"name\": \"sous dossier de test\", \"base\": false, \"created_at\": \"2020-03-14 18:07:06\", \"created_by\": { \"name\": \"soko admin updated\" }, \"parent\": { \"_id\": \"9d99929c-fab1-4c69-91e1-e54993fbca33\", \"name\": \"test\" } }, { \"_id\": \"63264c77-da93-4565-bccc-89a372acd483\", \"name\": \"xxxxx\", \"base\": false, \"created_at\": \"2020-03-21 23:32:10\", \"created_by\": { \"name\": \"soko admin updated\" }, \"parent\": null }, { \"_id\": \"b2addab7-2c59-4ac3-a546-f96a39ecf700\", \"name\": \"string\", \"base\": false, \"created_at\": \"2020-03-20 09:13:57\", \"created_by\": { \"name\": \"soko admin updated\" }, \"parent\": null }, { \"_id\": \"ea172ed4-494d-4b06-abb2-0af27a9e52e9\", \"name\": \"sous dossier de test 2\", \"base\": false, \"created_at\": \"2020-03-14 18:08:51\", \"created_by\": { \"name\": \"soko admin updated\" }, \"parent\": { \"_id\": \"9d99929c-fab1-4c69-91e1-e54993fbca33\", \"name\": \"test\" } } ], \"meta\": { \"pagination\": { \"total\": 12, \"count\": 12, \"per_page\": 15, \"current_page\": 1, \"total_pages\": 1, \"links\": {} } } }, \"message\": \"Folders retrieved successfully\" }"
            .replaceAll(" \"", "\"").replaceAll(" \\{", "{");

    public static final String singleFolder = "{ \"success\":true, \"presents\": { \"data\": { \"_id\": \"14f8c621-02b6-4238-92d3-3655cb294435\", \"name\": \"mydrive\", \"base\": false, \"created_at\": \"2020-03-26 19:14:26\", \"created_by\": { \"name\": \"soko admin updated\" }, \"parent\": null } }, \"message\": null }"
            .replaceAll(" \"", "\"").replaceAll(" \\{", "{");


    public static final String sf = "{\n" +
            "      \"_id\": \"994ec4c7-e1d3-480b-a45a-7dae42414a36\",\n" +
            "      \"extension\": \"png\",\n" +
            "      \"size\": \"435.953 KB\",\n" +
            "      \"original_name\": \"Capture d’écran du 2020-02-07 12-49-51.png\",\n" +
            "      \"url\": \"http://127.0.0.1:8000/soko//2987cc10450745e99aabc60346bf5b1e.png\",\n" +
            "      \"created_at\": \"2020-03-27 21:52:39\",\n" +
            "      \"folder\": {\n" +
            "        \"name\": \"test\"\n" +
            "      },\n" +
            "      \"created_by\": {\n" +
            "        \"name\": \"soko admin updated\"\n" +
            "      }\n" +
            "    }";
}

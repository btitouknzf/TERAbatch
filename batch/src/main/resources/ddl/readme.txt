このフォルダに含まれるファイルはTERASOLUNA-BATCHを起動するために必要なシステムテーブルのスキーマです。
batch-application.propertiesで指定することで、バッチ起動時にテーブルが存在しなければ作成します。

本来は「spring-batch-core-3.x.x.jarに含まれますがDB2の場合は作成されていなかったので以下のgithubを参考に自前で作成し、
クラスパス上に配置しています。
https://github.com/spring-projects/spring-batch/tree/master/spring-batch-core/src/main/resources/org/springframework/batch/core



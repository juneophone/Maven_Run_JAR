# Use IntelliJ IDEA Maven build can executive jar file. 


# Maven Build

<p align=center><img src="./images/MavenBuild_01.png" ></p>

# Pom.xml Setting

[pom.xml](pom.xml)

# Maven Compile JAR Package Setting

```java
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>2.4</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <classpathPrefix>libs/</classpathPrefix>
                            <mainClass>com.example.MainPage</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
```

# Copy the dependent jar package to the libs directory

```java
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>
                                ${project.build.directory}/libs
                            </outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
```

# Package the dependent packages together.


```java
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>2.4</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.example.MainPage</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
            </plugin>
```


# Use git-commit-id-plugin


```java
						<plugin>
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
                <version>4.9.10</version>
                <executions>
                    <execution>
                        <id>get-the-git-infos</id>
                        <!-- ?q?{?j?w???qinitialize -->
                        <phase>initialize</phase>
                        <goals>
                            <!-- ?????Grevision -->
                            <goal>revision</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <!-- ???d?????w???????A${project.basedir}?G???????????A?Y?]?tpom.xml?????????? -->
                    <dotGitDirectory>${project.basedir}/.git</dotGitDirectory>
                    <!-- false?G???y???|???????L???h?H???A?q?{??false?A?i?H???t?m -->
                    <verbose>false</verbose>
                    <!-- ?w?q???????????????????A?q?{???Gyyyy-MM-dd??T??HH:mm:ssZ -->
                    <dateFormat>yyyy-MM-dd HH:mm:ss</dateFormat>
                    <!-- git???????????U?????e???A?q?{??git?A?i?H???t?m -->
                    <prefix>git</prefix>
                    <!-- ????git?????????A?q?{false?G?????? -->
                    <generateGitPropertiesFile>true</generateGitPropertiesFile>
                    <!-- ????git???????????|???????W?A?q?{${project.build.outputDirectory}/git.properties -->
                    <generateGitPropertiesFilename>${project.build.outputDirectory}/git.properties</generateGitPropertiesFilename>
                    <!-- ????git?????????????A?q?{??properties -->
                    <format>json</format>
                    <!-- ?t?mgit-describe?R?O -->
                    <gitDescribe>
                        <skip>false</skip>
                        <always>false</always>
                        <dirty>-dirty</dirty>
                    </gitDescribe>
                </configuration>
            </plugin>
```

# execute_jar folder

Here are the executable JAR files.

# Execute Result

# The public JAR is separated. The libs are not together with the JAR.

Command : java -jar GitCommitHashTest-1.0-SNAPSHOT-master-431ece7.jar

<p align=center><img src="./images/MavenBuild_04.png" ></p>

# Package all public JARs together.

Command : java -jar GitCommitHashTest-1.0-SNAPSHOT-master-431ece7-jar-with-dependencies.jar

<p align=center><img src="./images/MavenBuild_02.png" ></p>
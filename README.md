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
                        <!-- �q�{�j�w���qinitialize -->
                        <phase>initialize</phase>
                        <goals>
                            <!-- �ؼСGrevision -->
                            <goal>revision</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <!-- �ˬd���ܮw�ڥؿ��A${project.basedir}�G���خڥؿ��A�Y�]�tpom.xml��󪺥ؿ� -->
                    <dotGitDirectory>${project.basedir}/.git</dotGitDirectory>
                    <!-- false�G���y���|�ɤ����L��h�H���A�q�{��false�A�i�H���t�m -->
                    <verbose>false</verbose>
                    <!-- �w�q���󤤩Ҧ��ɶ��榡�A�q�{�ȡGyyyy-MM-dd��T��HH:mm:ssZ -->
                    <dateFormat>yyyy-MM-dd HH:mm:ss</dateFormat>
                    <!-- git�ݩʤ�󤤦U�ݩʫe��A�q�{��git�A�i�H���t�m -->
                    <prefix>git</prefix>
                    <!-- �ͦ�git�ݩʤ��A�q�{false�G���ͦ� -->
                    <generateGitPropertiesFile>true</generateGitPropertiesFile>
                    <!-- �ͦ�git�ݩʤ����|�Τ��W�A�q�{${project.build.outputDirectory}/git.properties -->
                    <generateGitPropertiesFilename>${project.build.outputDirectory}/git.properties</generateGitPropertiesFilename>
                    <!-- �ͦ�git�ݩʤ��榡�A�q�{��properties -->
                    <format>json</format>
                    <!-- �t�mgit-describe�R�O -->
                    <gitDescribe>
                        <skip>false</skip>
                        <always>false</always>
                        <dirty>-dirty</dirty>
                    </gitDescribe>
                </configuration>
            </plugin>
```
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gobblin.writer.partitioner;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@Test(groups = {"gobblin.writer.partitioner"})
public class SchemaBasedWriterPartitionerTest {
  @Test
  public void partitionRecordTest() {
    Schema mockSchema = mock(Schema.class);
    GenericRecord mockRecord = mock(GenericRecord.class);
    String mockSchemaString = "returnSchemaString";

    when(mockRecord.getSchema()).thenReturn(mockSchema);
    when(mockSchema.toString()).thenReturn(mockSchemaString);

    SchemaBasedWriterPartitioner partitioner = new SchemaBasedWriterPartitioner(null, 0, 0);
    GenericRecord partitionRecord = partitioner.partitionForRecord(mockRecord);

    Assert.assertEquals(partitionRecord.get(SchemaBasedWriterPartitioner.SCHEMA_STRING), mockSchemaString);
  }
}

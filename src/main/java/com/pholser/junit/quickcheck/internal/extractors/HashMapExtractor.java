/*
 The MIT License

 Copyright (c) 2010-2011 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package com.pholser.junit.quickcheck.internal.extractors;

import java.util.ArrayList;
import java.util.HashMap;

import com.pholser.junit.quickcheck.ComponentizedRandomValueExtractor;
import com.pholser.junit.quickcheck.internal.random.SourceOfRandomness;

public class HashMapExtractor extends ComponentizedRandomValueExtractor<HashMap> {
    public HashMapExtractor() {
        super(HashMap.class);
    }

    @Override
    public HashMap<?, ?> extract(SourceOfRandomness random) {
        int size = random.nextInt(0, 100);

        HashMap<Object, Object> items = new HashMap<Object, Object>();
        for (int itemsAdded = 0; itemsAdded < size;) {
            Object key = componentExtractors.get(0).extract(random);
            Object value = componentExtractors.get(1).extract(random);
            if (!items.containsKey(key)) {
                items.put(key, value);
                ++itemsAdded;
            }
        }

        return items;
    }
}

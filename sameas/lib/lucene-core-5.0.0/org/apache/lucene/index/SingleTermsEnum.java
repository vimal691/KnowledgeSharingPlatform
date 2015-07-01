package org.apache.lucene.index;

/*
 *
 * Copyright(c) 2015, Samsung Electronics Co., Ltd.
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.
    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

import org.apache.lucene.search.MultiTermQuery;  // javadocs
import org.apache.lucene.util.BytesRef;

/**
 * Subclass of FilteredTermsEnum for enumerating a single term.
 * <p>
 * For example, this can be used by {@link MultiTermQuery}s
 * that need only visit one term, but want to preserve
 * MultiTermQuery semantics such as {@link
 * MultiTermQuery#getRewriteMethod}.
 */
public final class SingleTermsEnum extends FilteredTermsEnum {
  private final BytesRef singleRef;
  
  /**
   * Creates a new <code>SingleTermsEnum</code>.
   * <p>
   * After calling the constructor the enumeration is already pointing to the term,
   * if it exists.
   */
  public SingleTermsEnum(TermsEnum tenum, BytesRef termText) {
    super(tenum);
    singleRef = termText;
    setInitialSeekTerm(termText);
  }

  @Override
  protected AcceptStatus accept(BytesRef term) {
    return term.equals(singleRef) ? AcceptStatus.YES : AcceptStatus.END;
  }
  
}